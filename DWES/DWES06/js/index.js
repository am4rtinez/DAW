$(document).ready(function () {
    $(".alert .btn-close").click(function () {
        $(this).closest('.alert').hide();
    });

    //Funcion para que se muestren los tooltips de bootstrap.
    $(function () {
        $('[data-bs-toggle="tooltip"]').tooltip({
            html: true
        })
    })
});