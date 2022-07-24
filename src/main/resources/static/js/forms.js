$("#contact-form-group").submit(function (e) {
    e.preventDefault();
    $("#form-status").remove();
    submitForm($(this));
});

$(document).ready(function () {
    $("#lang-toggle").click(function (e) {
        e.preventDefault();
        let selectedOption = $(this).attr("href")
        if (selectedOption !== '') {
            document.cookie = "lang=" + selectedOption + "; expires=0; path=/";
            document.location.reload();
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