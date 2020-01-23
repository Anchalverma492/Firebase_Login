package com.yoekisoft.myapplication.FirebaseMsg;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.inappmessaging.FirebaseInAppMessagingClickListener;
import com.google.firebase.inappmessaging.model.Action;
import com.google.firebase.inappmessaging.model.CampaignMetadata;
import com.google.firebase.inappmessaging.model.InAppMessage;

public class InAppMsg implements FirebaseInAppMessagingClickListener {
    @Override
    public void messageClicked(@NonNull InAppMessage inAppMessage, @NonNull Action action) {
        String url = action.getActionUrl();
Log.d("Url",url);
        // Get general information about the campaign
        CampaignMetadata metadata = inAppMessage.getCampaignMetadata();
        Log.d("MetaData",metadata.toString());
    }
}
