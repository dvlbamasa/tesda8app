
$("#submitButton").click(function(event) {
    if($('#addSuccessIndicatorForm').valid()) {
        event.preventDefault();
        swal({
            title: "Add Success Indicator?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Success Indicator successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addSuccessIndicatorForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
