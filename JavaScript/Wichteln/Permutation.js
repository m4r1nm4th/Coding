document.getElementById("myForm").addEventListener("submit", (e) => {
    e.preventDefault();
    let formData = new FormData(e.target);
    console.log(Object.fromEntries(formData.entries()));
});
