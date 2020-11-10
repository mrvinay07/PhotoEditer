package com.teamvinay.newphotoediter.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DonateClient {/*implements PurchasesUpdatedListener, BillingClientStateListener {
    private final String SKU_ITEM = "";
    private Activity activity;
    private BillingClient billingClient;
    private DonateClientListener donateClientListener;
    private boolean isCheckLicense;

    public interface DonateClientListener {
        void acknowledgedPurchase(boolean z);

        void skuDetailsResult(List<SkuDetails> list);
    }

    public DonateClient(Activity activity2, DonateClientListener donateClientListener2, boolean z) {
        this.activity = activity2;
        this.donateClientListener = donateClientListener2;
        this.isCheckLicense = z;
    }

    public void setupBillingClient() {
        this.billingClient = BillingClient.newBuilder(this.activity).enablePendingPurchases().setListener(this).build();
        startConnection();
    }

    private void startConnection() {
        this.billingClient.startConnection(this);
    }

    public void onBillingSetupFinished(BillingResult billingResult) {
        if (billingResult == null || billingResult.getResponseCode() != 0) {
            if (this.isCheckLicense) {
                this.donateClientListener.acknowledgedPurchase(false);
            }
        } else if (this.isCheckLicense) {
            this.donateClientListener.acknowledgedPurchase(checkHasPurchaseAcknowledged());
        } else {
            querySkuDetails();
        }
    }

    public void onBillingServiceDisconnected() {
        Log.d("hihi", "Billing client disconnected from service");
        startConnection();
    }

    private void querySkuDetails() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("");
        this.billingClient.querySkuDetailsAsync(SkuDetailsParams.newBuilder().setSkusList(arrayList).setType(BillingClient.SkuType.INAPP).build(), new SkuDetailsResponseListener() {
            public final void onSkuDetailsResponse(BillingResult billingResult, List list) {
                DonateClient.lambda$querySkuDetails$0(DonateClient.this, billingResult, list);
            }
        });
    }

    public static *//* synthetic *//* void lambda$querySkuDetails$0(DonateClient donateClient, BillingResult billingResult, List list) {
        if (billingResult != null && billingResult.getResponseCode() == 0 && list != null) {
            donateClient.donateClientListener.skuDetailsResult(list);
        }
    }

    public void makePurchase(SkuDetails skuDetails) {
        this.billingClient.launchBillingFlow(this.activity, BillingFlowParams.newBuilder().setSkuDetails(skuDetails).build());
    }

    private void handleConsumablePurchasesAsync(List<Purchase> list) {
        for (Purchase purchaseToken : list) {
            this.billingClient.consumeAsync(ConsumeParams.newBuilder().setPurchaseToken(purchaseToken.getPurchaseToken()).build(), $$Lambda$DonateClient$36XzoauOuSgNoNt55pprQ739pfc.INSTANCE);
        }
    }

    static *//* synthetic *//* void lambda$handleConsumablePurchasesAsync$1(BillingResult billingResult, String str) {
        if (billingResult.getResponseCode() == 0) {
            Log.d("hihi", "Consumed the old purchase that hasn't already been acknowledged");
            return;
        }
        Log.d("hihi", String.format("Error consume the old purchase that hasn't already been acknowledged -> %s", new Object[]{Integer.valueOf(billingResult.getResponseCode())}));
    }

    private void acknowledgeNonConsumablePurchasesAsync(Purchase purchase) {
        this.billingClient.acknowledgePurchase(AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build(), new AcknowledgePurchaseResponseListener() {
            public final void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                DonateClient.lambda$acknowledgeNonConsumablePurchasesAsync$2(DonateClient.this, billingResult);
            }
        });
    }

    public static *//* synthetic *//* void lambda$acknowledgeNonConsumablePurchasesAsync$2(DonateClient donateClient, BillingResult billingResult) {
        if (billingResult.getResponseCode() == 0) {
            donateClient.donateClientListener.acknowledgedPurchase(true);
        }
    }

    private boolean checkHasPurchaseAcknowledged() {
        List<Purchase> purchasesList;
        Purchase.PurchasesResult queryPurchases = this.billingClient.queryPurchases(BillingClient.SkuType.INAPP);
        if (queryPurchases.getResponseCode() != 0 || (purchasesList = queryPurchases.getPurchasesList()) == null) {
            return false;
        }
        for (Purchase next : purchasesList) {
            if (next.isAcknowledged()) {
                return true;
            }
            if (next.getPurchaseState() != 2) {
                handleConsumablePurchasesAsync(Collections.singletonList(next));
            }
        }
        return false;
    }

    @SuppressLint({"SwitchIntDef"})
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> list) {
        if (billingResult != null) {
            int responseCode = billingResult.getResponseCode();
            if (responseCode != 7) {
                switch (responseCode) {
                    case 0:
                        if (list != null) {
                            for (Purchase next : list) {
                                if (!next.isAcknowledged()) {
                                    acknowledgeNonConsumablePurchasesAsync(next);
                                } else {
                                    this.donateClientListener.acknowledgedPurchase(true);
                                }
                            }
                            return;
                        }
                        return;
                    case 1:
                        Log.d("hihi", "onPurchasesUpdated() user canceled");
                        return;
                    default:
                        return;
                }
            } else {
                this.donateClientListener.acknowledgedPurchase(checkHasPurchaseAcknowledged());
            }
        }
    }

    private class DonationException extends Exception {
        private String action;
        private int responseCode;

        private String billingCodeName(int i) {
            switch (i) {
                case -2:
                    return "FEATURE_NOT_SUPPORTED";
                case -1:
                    return "SERVICE_DISCONNECTED";
                case 0:
                    return "OK";
                case 1:
                    return "USER_CANCELED";
                case 2:
                    return "SERVICE_UNAVAILABLE";
                case 3:
                    return "BILLING_UNAVAILABLE";
                case 4:
                    return "ITEM_UNAVAILABLE";
                case 5:
                    return "DEVELOPER_ERROR";
                case 6:
                    return "ERROR";
                case 7:
                    return "ITEM_ALREADY_OWNED";
                case 8:
                    return "ITEM_NOT_OWNED";
                default:
                    return "Not Know??";
            }
        }

        DonationException(String str, int i) {
            this.action = str;
            this.responseCode = i;
        }

        public String getMessage() {
            return this.action + " unsuccessful - responseCode = " + billingCodeName(this.responseCode);
        }
    }*/
}