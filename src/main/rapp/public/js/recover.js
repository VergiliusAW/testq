window.onload = function () {
    function bclick() {
        let promise = new Promise(((resolve, reject) => {
            resolve(fetch("/api/recover").then())
        }))
    }

    const el = document.getElementById("rec-button");
    el.addEventListener("click", bclick, false);
}