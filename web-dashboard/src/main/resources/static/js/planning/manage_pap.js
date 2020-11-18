$("#submitButton1").click(function(event) {
    if($('#addPapForm').valid()) {
        event.preventDefault();
        swal({
            title: "Add P/A/P?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("P/A/P successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addPapForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});

$("#submitButton2").click(function(event) {
    if($('#deletePapForm').valid()) {
        event.preventDefault();
        swal({
            title: "Delete P/A/P?",
            text: "Once deleted, the P/A/P will not be available.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("P/A/P successfully deleted!", {
                        icon: "success",
                    }).then((value) => {
                        $('#deletePapForm').submit();
                    });
                } else {
                    swal("Confirm first before deleting.");
                }
            });
    }
});