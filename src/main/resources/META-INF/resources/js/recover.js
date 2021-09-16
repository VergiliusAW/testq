window.onload = function () {
    function bclick() {
        console.log("aaa");
    }

    const el = document.getElementById("rec-button");
    el.addEventListener("click", bclick, false);
}