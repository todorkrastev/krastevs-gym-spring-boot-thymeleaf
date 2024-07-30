let acc = document.getElementsByClassName("accordion");
let i;
let activePanel = null;

for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function () {
        if (activePanel && activePanel !== this) {
            activePanel.classList.remove("active");
            let prevPanel = activePanel.nextElementSibling;
            prevPanel.style.maxHeight = null;
            prevPanel.style.padding = "0";
        }

        this.classList.toggle("active");
        let panel = this.nextElementSibling;
        if (panel.style.maxHeight) {
            panel.style.maxHeight = null;
            panel.style.padding = "0";
        } else {
            panel.style.maxHeight = panel.scrollHeight + 64 + "px";
            panel.style.padding = "16px";
        }

        activePanel = this.classList.contains("active") ? this : null;
    });
}