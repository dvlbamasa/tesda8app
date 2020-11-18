
$("#submitButton").click(function(event) {
    if($('#updateProgRegForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Registered Program?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Registered Program successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#updateProgRegForm').submit();
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
        title: "Delete Registered Program?",
        text: "Once deleted, the registered program will not be available.",
        icon: "warning",
        buttons: ["No", "Yes"],
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                swal("Registered Program successfully deleted!", {
                    icon: "success",
                }).then((value) => {
                    window.location.href = currentElement.attr('href');
                });
            } else {
                swal("Confirm first before deleting.");
            }
        });
});