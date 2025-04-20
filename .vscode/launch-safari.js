const { exec } = require("child_process");

// Open Safari to localhost
exec("open -a Safari http://localhost:8080", (err) => {
  if (err) {
    console.error("Failed to launch Safari:", err);
  } else {
    console.log("Safari launched at http://localhost:8080");
  }
});
