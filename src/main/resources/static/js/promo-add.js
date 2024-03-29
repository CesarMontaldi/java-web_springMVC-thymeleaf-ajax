//submit do formulario para o controller
$("#form-add-promo").submit(function(event) {
    //bloquear o comportamento padrão do 'submit'
    event.preventDefault();

    var promo = {};
    promo.linkPromocao = $("#linkPromocao").val();
    promo.descricao = $("#descricao").val();
    promo.preco = $("#preco").val();
    promo.titulo = $("#titulo").val();
    promo.categoria = $("#categoria").val();
    promo.linkImagem = $("#linkImagem").attr("src");
    promo.site = $("#site").text();

    console.log("promo > ", promo);

    $.ajax({
        method: "POST",
        url: "/promocao/save",
        data: promo,
        beforeSend: function() {
            // removendo as mensagens
            $("span").closest('.error-span').remove();

            // remover as bordas vermelhas
            $("#categoria").removeClass("is-invalid");
            $("#preco").removeClass("is-invalid");
            $("#linkPromocao").removeClass("is-invalid");
            $("#titulo").removeClass("is-invalid");

            // habilita o loading
            $("#form-add-promo").hide();
            $("#loader-form").addClass("loader").show();
        },
        success: function() {
            $("#form-add-promo").each(function() {
                this.reset();
            });
            $("#site").text("");
            $("#linkImagem").attr("src", "/images/promo-dark.png");
            $("#alert")
                .removeClass("alert alert-danger")
                .addClass("alert alert-success")
                .text("OK! Promoção solva com sucesso.");
        },
        statusCode: {
            422: function(xhr) {
                console.log("status error: ", xhr.status);
                var errors = $.parseJSON(xhr.responseText);
                $.each(errors, function(key, val) {
                    $("#" + key).addClass("is-invalid");
                    $("#error-" + key)
                        .addClass("invalid-feedback")
                        .append("<span class='error-span'>" + val + "</span>")
                });
            }
        },
        error: function(xhr) {
            console.log("> error: ", xhr.responseText);
            $("#alert").addClass("alert alert-danger").text("Não foi possível salvar esta promoção.");
        },
        complete: function() {
            $("#form-add-promo").fadeOut(800, function() {
                $("#form-add-promo").fadeIn(250);
                $("#loader-form").removeClass("loader");
            });
        }
    });
});



// função para capturar as meta tags
$("#linkPromocao").on('change', function() {
    var url = $(this).val();

    if (url.length > 7) {

        $.ajax({
            method: "POST",
            url: "/meta/info?url=" + url,
            cache: false,
            beforeSend: function() {
                $("#alert").removeClass("alert alert-danger alert-success").text("");
                $("#titulo").val("");
                $("#descricao").val("");
                $("#site").text("");
                $("#linkImagem").attr("src", "");
                $("#loader-img").addClass("loader");
            },
            success: function(data) {
                console.log(data);
                $("#titulo").val(data.title);
                $("#descricao").val(data.descricao);
                $("#site").text(data.site.replace("@", ""));
                $("#linkImagem").attr("src", data.image);
            },
            statusCode: {
                404: function(){
                    $("#alert").addClass("alert alert-danger").text("Nenhuma informação pode ser recuperada dessa url.");
                        $("#linkImagem").attr("src", "/images/promo-dark.png");
                    }

            },
            error: function() {
                    $("#alert").addClass("alert alert-danger").text("Ops... algo deu errado, tente mais tarde.");
                    $("#linkImagem").attr("src", "/images/promo-dark.png");
            },
            complete: function() {
                $("#loader-img").removeClass("loader");
            }
        });
    }
})

// opcoes para caixa de mensagens com avisos.
//404: function() {
//    $("#form-add-promo").before("<div class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Nenhuma informação pode ser recuperada dessa url.</strong><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>");
//},
//500: function() {
//    $("#form-add-promo").before("<div class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Ops... algo deu errado, tente mais tarde.</strong><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>");
//}

//404: function(){
//        $("#alert").addClass("alert alert-warning alert-dismissible fade show")
//            .text("Nenhuma informação pode ser recuperada dessa url.")
//            .fadeIn()
//            .fadeOut(5000, function() { $("#alert")
//            .remove();});
//            $("#linkImagem").attr("src", "/images/promo-dark.png");
//        }
