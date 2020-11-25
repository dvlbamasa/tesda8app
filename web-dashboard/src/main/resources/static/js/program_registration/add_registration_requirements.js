$("#submitButton").click(function(event) {
    if ( $('#addProgRegReqForm').valid()) {
        event.preventDefault();
        swal({
            title: "Add Registration Requirements?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Program Registration Requirements successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addProgRegReqForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
