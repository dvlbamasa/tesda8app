$("#submitButton").click(function(event) {
    if($('#monthlyReportForm').valid()) {
        event.preventDefault();
        swal({
            title: "Create New Monthly Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Monthly Report successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#monthlyReportForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});