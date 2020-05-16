let main = {
    init: function() {
        let _this = this;
        $("#btn-save").on("click", () => {
            _this.save();
        });
        $("#btn-update").on("click", () => {
            _this.update();
        });
        $("#btn-delete").on("click", () => {
            _this.delete();
        });
        console.log("main loaded complete...");
    },
    save: function() {
        const data = {
            title: $("#title").val(),
            author: $("#author").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "post",
            url: "/api/v1/posts",
            contentType: "application/json; charset=UTF-8",
            data:JSON.stringify(data)
        }).done(() => {
            alert("save posts complete...");
            window.location.href = "/";
        }).fail((error) => {
            alert("error!");
            console.log(JSON.stringify(error));
        });
    },
    update: function() {
        const data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        const id = $("#id").val();

        $.ajax({
            type: "put",
            url: "/api/v1/posts/" + id,
            contentType: "application/json; charset=UTF-8",
            data:JSON.stringify(data)
        }).done(() => {
            alert("update posts complete...");
            window.location.href = "/posts/update/" + id;
        }).fail((error) => {
            alert("error!");
            console.log(JSON.stringify(error));
        });
    },
    delete: function() {
        const id = $("#id").val();
        $.ajax({
            type: "delete",
            url: "/api/v1/posts/" + id
        }).done(() => {
            alert("delete posts complete...");
            window.location.href = "/";
        }).fail((error) => {
            alert("error!");
            console.log(JSON.stringify(error));
        });
    }
};

main.init();