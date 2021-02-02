
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


$(".deleteOfficial").click(function(event) {
    event.preventDefault();
    var currentElement = $(this);
    swal({
        title: "Delete Official?",
        text: "Once deleted, the official will not be available.",
        icon: "warning",
        buttons: ["No", "Yes"],
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                swal("Official successfully deleted!", {
                    icon: "success",
                }).then((value) => {
                    window.location.href = currentElement.attr('href');
                });
            } else {
                swal("Confirm first before deleting.");
            }
        });
});


$(".deleteTrainer").click(function(event) {
    event.preventDefault();
    var currentElement = $(this);
    swal({
        title: "Unlink TVET Trainer?",
        text: "Once unlinked, you will need to link the TVET trainer again.",
        icon: "warning",
        buttons: ["No", "Yes"],
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                swal("Trainer successfully unlinked!", {
                    icon: "success",
                }).then((value) => {
                    window.location.href = currentElement.attr('href');
                });
            } else {
                swal("Confirm first before deleting.");
            }
        });
});


$(".deleteStaff").click(function(event) {
    event.preventDefault();
    var currentElement = $(this);
    swal({
        title: "Delete Staff?",
        text: "Once deleted, the staff will not be available.",
        icon: "warning",
        buttons: ["No", "Yes"],
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                swal("Staff successfully deleted!", {
                    icon: "success",
                }).then((value) => {
                    window.location.href = currentElement.attr('href');
                });
            } else {
                swal("Confirm first before deleting.");
            }
        });
});