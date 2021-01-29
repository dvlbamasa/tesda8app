
$("#submitButton").click(function(event) {
    if($('#addTrainerForm').valid()) {
        event.preventDefault();
        swal({
            title: "Add New TVET Trainer?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("TVET Trainer successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addTrainerForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});