window.onload = function () {
    document.getElementById('page2').style.display="none";

    var handleNextPage = ( function () {
        return function () {
            console.log('pumasok');
            document.getElementById('page2').style.display="";
            document.getElementById('page1').style.display="none";
        }
    });
}
