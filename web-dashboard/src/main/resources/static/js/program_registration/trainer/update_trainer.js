$("#submitButton").click(function(event) {
    if ( $('#updateTrainer').valid()) {
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
                        $('#updateTrainer').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
