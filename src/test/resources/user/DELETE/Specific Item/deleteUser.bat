@echo off
curl -X DELETE -o %~n0.response.json -d @%~n0.request.json -H "Content-Type: application/json" "http://localhost:8080/user/3"
