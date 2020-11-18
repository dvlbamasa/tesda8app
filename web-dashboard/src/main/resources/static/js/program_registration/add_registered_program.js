$("#submitButton").click(function(event) {
    if ( $('#addProgRegForm').valid()) {
        event.preventDefault();
        swal({
            title: "Add Registered Program?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Registered Program successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addProgRegForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
