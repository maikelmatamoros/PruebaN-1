$(document).ready(function () {
    $("#LoginForm").validate({
        rules: {
            Email: {
                required: true,
                email: true
            },
            Password: {
                required: true,
                minlength: 8
            }
        }
    });
})

$("#LoginForm").submit(function (e) {
    e.preventDefault();
    if ($("#LoginForm").valid()) {
        var interval = null;
        $.ajax({
            url: "/Home/Authentication",
            data: {
                Email: $("#Email").val(),
                Password: $("#Password").val(),
            },
            type: "POST",
            dataType: "json",
            beforeSend: function () {
                i = 0;
                $("#login-btn").prop("disabled", true);
                interval = setInterval(function () {
                    i = ++i % 4;
                    $("#login-btn").html("Processing" + Array(i + 1).join("."));
                }, 500);
            },
            success: function (result) {
                window.location.replace("/Home/Index");    
            },
            error: function (errorMessage) {
                clearInterval(interval);
                $("#login-btn").html("Invalid Credential, Try Again!");
                $("#login-btn").css("background-color", "red");
                setTimeout(function () {
                    $("#login-btn").prop("disabled", false);
                    $("#login-btn").html("Log In");
                    $("#login-btn").css("background-color", "#7f5feb");
                }, 4000);
            }

        });
        
    }
})

// ParticlesJS

// ParticlesJS Config.
particlesJS("particles-js", {
  "particles": {
    "number": {
      "value": 60,
      "density": {
        "enable": true,
        "value_area": 800
      }
    },
    "color": {
      "value": "#ffffff"
    },
    "shape": {
      "type": "circle",
      "stroke": {
        "width": 0,
        "color": "#000000"
      },
      "polygon": {
        "nb_sides": 5
      }
    },
    "opacity": {
      "value": 0.1,
      "random": false,
      "anim": {
        "enable": false,
        "speed": 1,
        "opacity_min": 0.1,
        "sync": false
      }
    },
    "size": {
      "value": 6,
      "random": false,
      "anim": {
        "enable": false,
        "speed": 40,
        "size_min": 0.1,
        "sync": false
      }
    },
    "line_linked": {
      "enable": true,
      "distance": 150,
      "color": "#ffffff",
      "opacity": 0.1,
      "width": 2
    },
    "move": {
      "enable": true,
      "speed": 1.5,
      "direction": "top",
      "random": false,
      "straight": false,
      "out_mode": "out",
      "bounce": false,
      "attract": {
        "enable": false,
        "rotateX": 600,
        "rotateY": 1200
      }
    }
  },
  "interactivity": {
    "detect_on": "canvas",
    "events": {
      "onhover": {
        "enable": false,
        "mode": "repulse"
      },
      "onclick": {
        "enable": false,
        "mode": "push"
      },
      "resize": true
    },
    "modes": {
      "grab": {
        "distance": 400,
        "line_linked": {
          "opacity": 1
        }
      },
      "bubble": {
        "distance": 400,
        "size": 40,
        "duration": 2,
        "opacity": 8,
        "speed": 3
      },
      "repulse": {
        "distance": 200,
        "duration": 0.4
      },
      "push": {
        "particles_nb": 4
      },
      "remove": {
        "particles_nb": 2
      }
    }
  },
  "retina_detect": true
});