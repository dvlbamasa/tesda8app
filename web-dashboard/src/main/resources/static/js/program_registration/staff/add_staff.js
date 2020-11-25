$("#submitButton").click(function(event) {
    if ( $('#addStaff').valid()) {
        event.preventDefault();
        swal({
            title: "Add Non-Teaching Staff?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Staff successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addStaff').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
