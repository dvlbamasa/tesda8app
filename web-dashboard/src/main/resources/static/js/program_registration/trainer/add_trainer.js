$("#submitButton").click(function(event) {
    if ( $('#addTrainer').valid()) {
        event.preventDefault();
        swal({
            title: "Add Trainer?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Trainer successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addTrainer').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
