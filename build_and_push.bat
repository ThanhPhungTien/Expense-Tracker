@echo off

@REM echo Running 'docker build -t lvbang/ptthanh-expense-tracker .'
@REM call docker buildx create --use
call docker build -t lvbang/ptthanh-expense-tracker .
if %errorlevel% neq 0 goto error

echo Running 'docker push lvbang/ptthanh-expense-tracker'
call docker push lvbang/ptthanh-expense-tracker
if %errorlevel% neq 0 goto error

echo All commands executed successfully.
goto end

:error
echo An error occurred. Aborting.

:end
pause