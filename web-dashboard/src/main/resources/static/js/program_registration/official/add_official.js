$("#submitButton").click(function(event) {
    if ( $('#addOfficial').valid()) {
        event.preventDefault();
        swal({
            title: "Add Official?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Official successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addOfficial').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
