$("#contact-form-group").submit(function (e) {
    e.preventDefault();
    $("#form-status").remove();
    submitForm($(this));
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