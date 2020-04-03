$(document).ready(function close() {
    let closable = localStorage.getItem("closable");
    if(closable === "true") {
        window.close();
        localStorage.removeItem("closable");
    }
})