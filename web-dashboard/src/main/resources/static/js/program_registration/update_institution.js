
$("#submitButton").click(function(event) {
    if($('#updateInstitutionForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Institution?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Institution successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#updateInstitutionForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});


$("#deleteButton").click(function(event) {
    event.preventDefault();
    var currentElement = $(this);
    swal({
        title: "Delete Institution?",
        text: "Once deleted, the institution will not be available.",
        icon: "warning",
        buttons: ["No", "Yes"],
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                swal("Institution successfully deleted!", {
                    icon: "success",
                }).then((value) => {
                    window.location.href = currentElement.attr('href');
                });
            } else {
                swal("Confirm first before deleting.");
            }
        });
});