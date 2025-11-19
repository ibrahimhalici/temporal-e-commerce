package app

import (
	"go.temporal.io/sdk/workflow"
	"time"
)

type Product struct {
	id         int
	name       string
	unitPrice  float64
	quantity   int
	totalPrice float64
}

type Order struct {
	id         int
	userId     int
	products   []Product
	orderDate  string
	status     string
	totalPrice float64
}

func PaymentWorkflow(ctx workflow.Context, order Order) (bool, error) {
	ao := workflow.ActivityOptions{
		StartToCloseTimeout: 10 * time.Second,
	}
	ctx = workflow.WithActivityOptions(ctx, ao)
	logger := workflow.GetLogger(ctx)
	logger.Info("payment workflow started", "orderId ", order.id)

	fraudFuture := workflow.ExecuteActivity(ctx, FraudRiskCheck, order)
	payFuture := workflow.ExecuteActivity(ctx, Pay, order)
	notificationFuture := workflow.ExecuteActivity(ctx, PaymentNotification, order)

	var fraudResult bool
	var payResult bool
	var notificationResult bool

	err1 := fraudFuture.Get(ctx, &fraudResult)
	if err1 != nil {
		logger.Error("FraudRiskCheck activity failed.", "Error", err1)
		return false, err1
	}
	logger.Info("FraudRiskCheck completed", "result", fraudResult)

	err2 := payFuture.Get(ctx, &payResult)
	if err2 != nil {
		logger.Error("Pay activity failed.", "Error", err2)
		return false, err2
	}
	logger.Info("Pay completed", "result", payResult)

	err3 := notificationFuture.Get(ctx, &notificationResult)
	if err3 != nil {
		logger.Error("PaymentNotification activity failed.", "Error", err3)
		return false, err3
	}

	logger.Info("PaymentNotification completed", "result", notificationResult)
	overallSuccess := fraudResult && payResult && notificationResult

	logger.Info("payment workflow completed.", "overallSuccess", overallSuccess)

	return overallSuccess, nil
}
