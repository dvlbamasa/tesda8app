
$("#submitButton").click(function(event) {
    if($('#updateTrainerForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Trainer?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Trainer successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#updateTrainerForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});


$("#deleteTvetTrainer").click(function(event) {
    event.preventDefault();
    var currentElement = $(this);
    swal({
        title: "Delete TVET Trainer?",
        text: "Once deleted, the trainer will not be available.",
        icon: "warning",
        buttons: ["No", "Yes"],
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                swal("Trainer successfully deleted!", {
                    icon: "success",
                }).then((value) => {
                    window.location.href = currentElement.attr('href');
                });
            } else {
                swal("Confirm first before deleting.");
            }
        });
});


$(".deleteCertificate").click(function(event) {
    event.preventDefault();
    var currentElement = $(this);
    swal({
        title: "Delete Certificate?",
        text: "Once deleted, the certificate will not be available.",
        icon: "warning",
        buttons: ["No", "Yes"],
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                swal("Certificate successfully deleted!", {
                    icon: "success",
                }).then((value) => {
                    window.location.href = currentElement.attr('href');
                });
            } else {
                swal("Confirm first before deleting.");
            }
        });
});