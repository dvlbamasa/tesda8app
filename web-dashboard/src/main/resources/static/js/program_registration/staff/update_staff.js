$("#submitButton").click(function(event) {
    if ( $('#updateStaff').valid()) {
        event.preventDefault();
        swal({
            title: "Update Staff?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Staff successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#updateStaff').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
