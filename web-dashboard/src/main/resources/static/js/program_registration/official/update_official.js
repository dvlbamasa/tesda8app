$("#submitButton").click(function(event) {
    if ( $('#updateOfficial').valid()) {
        event.preventDefault();
        swal({
            title: "Update Official?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Official successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#updateOfficial').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
