$APP_MODULE = "app.main:app"
$IPHOST = "0.0.0.0"
$PORT = "8001"

uvicorn --reload --host $IPHOST --port $PORT "$APP_MODULE"