@echo off
rem curl -o %~n0.response.json -d @%~n0.request.json -H "Content-Type: application/json" "http://localhost:8080/%~n0"
curl -o %~n0.response.json -d @%~n0.request.json -H "Content-Type: application/json" "http://localhost:8080/user/1"
