$(document).ready(function () {
    $(".alert .btn-close").click(function () {
        $(this).closest('.alert').hide();
    });
});