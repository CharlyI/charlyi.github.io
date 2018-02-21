(function($, undefined) {
	$(function() {
		
		var $OKButton = $("#OKButton"),
			$answer = $("#answer"),
			$varA = $("#varA"),
			$varB = $("#varB"),
			$msg = $("#msg"),
			$multiplicTable = $("#multiplicTable"),
			$progress = $("#progress");
			
			
		var i = 1, 	// counter
			n; 		// all questions
		
		// Set mode
		var mode = $("#mode").attr("value");
		
		// Set number of questions
		if (mode == "all") {
			$varA.html(Math.floor(Math.random()*10 + 1));
			n = 20;
		} else {
			$varA.html(mode);
			n = 10;
		}
		
		var mistakeCounter = 0;
		
		// Set context
		var context = $("#context").attr("value");
			
			
			
		// Initialization of variables
		
		$varB.html(Math.floor(Math.random()*10 + 1));
		
		// Set focus to input
		$answer.focus();
		
		// Set $("#progress") content
		setProgress();
				
		
		// if OK button pressed
		$OKButton.click(function(event) {
			var a = $varA.html(),
				b = $varB.html(),
				ans = $answer.val(),
				trueAnswer = a * b;
				var answerStr;
			
			if (ans == trueAnswer) {
				$msg.html("Правильно!");
				answerStr = '[Верно] ';
				
				if (mode == "all") {
					$varA.html(Math.floor((Number(a) + 1)%10));
				} else {
					$varA.html(mode);
				}
				$varB.html(Math.floor((Number(b) + 7)%10));
				
				if (i == n) {
					$multiplicTable.html('Пожалуйста, подождите...');
					var path = 'multiplic/finish?answerCount='.concat(i).concat('&mistakeCount=').concat(mistakeCounter);
					$(location).attr('href', path);
				} else {
					i++;
					setProgress();
				}
			} else {
				$msg.html("Неправильно!");
				answerStr = '[Неверно] ';
				mistakeCounter++;
			}
			
			$answer.val("");
			$answer.focus();
			
			answerStr = answerStr.concat(a).concat(' x ').concat(b).concat(' = ').concat(ans).concat('\n');
			//console.log(answerStr);
			
			// send answer to server
			$.ajax({
				url: context.concat("/multiplic/getanswer"),
				type: "POST",
				async: false,
				timeout: 30000,
				data: {"answer": answerStr},
				success: function (data) {
					
				}
			});
			
			// every 10: A + 3
			if (i % 10 == 0 && mode == "all") {
				$varA.html(Math.floor((Number(a) + 3)%10));
			}
			
		});
		
		function setProgress() {
			$progress.html((i).toString().concat(" из ").concat(n.toString()));
		}
		
		// if enter pressed
		$(answer).keypress(function(e) {
			if(e.which == 13) {
				$OKButton.click();
			}
		});
		
	});
})(jQuery);