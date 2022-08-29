$("#contact-form-group").submit(function (e) {
    e.preventDefault();
    $("#form-status").remove();
    submitForm($(this));
});

function setLanguage(value) {
    if (getCookie("lang") !== value) {
        document.cookie = "lang=" + value + "; expires=0; path=/";
        document.location.reload();
    }
}

function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

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