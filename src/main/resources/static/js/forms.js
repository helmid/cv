$("#contact-form-group").submit(function (e) {
    e.preventDefault();
    $("#form-status").remove();
    submitForm($(this));
});

function setLanguage(value) {
    if ($.cookie('lang') == null) {
        let cookie = "lang=" + value + "; expires=0; path=/"
        document.cookie = cookie;
        document.location.reload();
    }
}

let lang = $('html')[0].lang;
setLanguage(lang);

$(document).ready(function () {
    $("#lang-toggle").click(function (e) {
        e.preventDefault();
        let selectedOption = $(this).attr("href")
        if (selectedOption !== '') {
            setLanguage(selectedOption);
        }
    });
});

function submitForm(form) {
    $.ajax({
        type: form.attr("method"),
        url: form.attr("action"),
        data: form.serialize(),
        success: function (data) {
            $(data).appendTo('body');
            $($("#form-status")).modal();
        },
    });
}