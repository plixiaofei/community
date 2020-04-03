function postComment() {
    const comment = $('#comment');
    const parentId = $('#hidden_id');
    if(comment === "") {
        alert("请回复评论内容");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType :"application/json",
        data: JSON.stringify({
            "parentId" :parentId.val(),
            "comment" :comment.val(),
            "type" :1
        }),
        success: function(response){
            if(response.code === 200) {
                //刷新按钮，可以从数据库拿到数据
                location.reload();
                // comment.val("");
            }
            else if(response.code === 1004) {
                    let isAccepted = confirm(response.message);
                    if(isAccepted) {
                        window.open("/loginIn");
                        localStorage.setItem("closable",true);
                    }
            }
        },
        dataType: "json"
    });
}