/* 
 *
 */

function PieChart(question, answers) {
    
    this.question = question;
    this.answers = answers;
    
    var textColor = "white",
    colors = ["aqua", "blue", "fuchsia", "green", "lime", "maroon", 
        "navy", "olive", "purple", "red", "silver", "teal", "yellow"];
    
    function tooltip(context, message) {
        context.beginPath();
        context.fillStyle = "black";
        context.fillRect(message.x + 5, message.y + 5, message.content.length * 8, 30);
        
        context.font = "12pt Calibri";
        context.fillStyle = "white";
        context.textBaseline = "top";
        context.fillText(message.content, message.x + 10, message.y + 10);
        context.closePath();
    }
    
    function calcDisplayValues(answers) {
        var oldAngle = 0, i = 0,
        votes = _.foldl(answers, function(memo, answer){return memo + answer.votes;}, 0);
        
        return _.map(answers, function (answer) {
            var angle = ((2 * Math.PI * answer.votes) / votes) + oldAngle,
            pourcentage = ((angle - oldAngle) * 100) / (2*Math.PI),
            resultText = answer.answer + " : " + Math.round(pourcentage * 100)/100 + "%",
            color = colors[i++],
            
            res = {color : color, resultText : resultText,
                startAngle : oldAngle, endAngle : angle, rectY : i*20, textY : i*21};
            
            oldAngle = angle;
            if (i >= colors.length) {
                i = 0;
            }
            console.log(resultText);
            return res;
        });
    }
    
    this.draw = function(canvasId, width, height) {
        //        var canvas = document.getElementById(canvasId),
        var kin = new Kinetic_2d(canvasId),
        canvas = kin.getCanvas(),
        context = canvas.getContext("2d"),
        radius = height / 2 - 20,
        center = {x: radius + 10, y: radius + 10},
        displayValues;
        
        if (!context) {
            console.log("Your browser doesn't support canvas.");
            return;
        }
        
        displayValues = calcDisplayValues(this.answers);
        context.font = "12pt Calibri";
        context.textBaseline = "top";
        
        kin.setDrawStage(function() {
            kin.clear();
            var message;
            _.each(displayValues, function(display) {
                context.beginPath();
                kin.beginRegion();
                // draw chart
                context.fillStyle = display.color;
                context.arc(center.x, center.y, radius, display.startAngle, display.endAngle);
                context.lineTo(center.x, center.y);
                
                kin.addRegionEventListener("mousemove", function(){
                    var mousePos = kin.getMousePos();
                    message = {
                        x: mousePos.x,
                        y: mousePos.y,
                        content: display.resultText
                    };
                });
                
                kin.closeRegion();
                
                // draw description
                context.fillRect(42+ radius*2, 42 + display.rectY, 10, 5);
                context.closePath();
                context.fill();
                context.fillStyle = textColor;
                context.fillText(display.resultText, 64 + radius*2, 32 + display.textY);
            });
            
            if (message !== undefined) {
                tooltip(context, message);
            }
        });
    };
}

function showResults(poll) {
    var canvasId = "poll-canvas-" + poll.id,
    pieChart = new PieChart(poll.question, poll.answers),
    canvas = $("#" + canvasId);
    
    pieChart.draw(canvasId, canvas.width(), canvas.height());
    canvas.removeClass("hide");
}

$(document).ready(function() {
    
    $(".poll-form").submit(function (){
        var form = $(this);
        $.ajax({
            type: form.attr("method"),
            url: form.attr("action"),
            data: form.serialize(),
            success: function(poll) {
                form.addClass("hide");
                showResults(poll);
            },
            error: function() {
                form.children(".flash-error").removeClass("hide");
            }
        });
        return false;
    });
    
});
