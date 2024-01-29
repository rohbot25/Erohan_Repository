var button = $("#fill");
var button2 = $("#change");
var button3 = $("#check");
button.click(function() {
    console.log(button3.text());
    if (button3.text() === "Check On") {
        $.ajax({
            url: "/check_on",
            type: "post",
            success: function(response) {
                console.log(response);
                button3.text("Check Off");
            }
        });
    } else {
        $.ajax({
            url: "/check_off",
            type: "post",
            success: function() {
                button3.text("Check On");
            }
        })
    }
});

button.click(function() {
    console.log(button.text());
    if (button.text() === "Fill") {
        $.ajax({
            url: "/fill",
            type: "post",
            success: function(response) {
                console.log(response);
            }
        });
    }
});

button.click(function() {
    console.log(button2.text());
    if (button2.text() === "Change ") {
        $.ajax({
            url: "/change",
            type: "post",
            success: function(response) {
                console.log(response);
            }
        });
    } 
});