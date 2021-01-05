function detailsModal(id) {
    $.ajax({
        url: "/audit/" + id + "/details",
        success: function (response) {
            $('#detailsModalHolder').html(response);
            $('#detailsModal').modal();
        }
    })
}