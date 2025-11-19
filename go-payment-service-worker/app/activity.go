package app

import (
	"context"
	"go.temporal.io/sdk/activity"
	"math/rand/v2"
	"time"
)

func FraudRiskCheck(ctx context.Context, order Order) (bool, error) {
	logger := activity.GetLogger(ctx)
	logger.Info("fraud and risk activity simulation", "orderId", order.id)
	time.Sleep(time.Duration(rand.IntN(10)))
	return true, nil
}

func Pay(ctx context.Context, order Order) (bool, error) {
	logger := activity.GetLogger(ctx)
	logger.Info("payment completion activity simulation", "orderId", order.id)
	time.Sleep(time.Duration(rand.IntN(10)))
	return true, nil
}

func PaymentNotification(ctx context.Context, order Order) (bool, error) {
	logger := activity.GetLogger(ctx)
	logger.Info("payment notification activity simulation", "orderId", order.id)
	time.Sleep(time.Duration(rand.IntN(10)))
	return true, nil
}
