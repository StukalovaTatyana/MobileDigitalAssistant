package ru.asu.mobiledigitalassistant.ui.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("1 пара 08:30 - 10:00 \r\n2 пара 10:10 - 11:40 \r\n3 пара 11:50 - 13:20 \r\n4 пара 13:40 - 15:10 \r\n5 пара 15:20 - 16:50 \r\n6 пара 16:55 - 18:25 \r\n7 пара 18:30 - 20:00 \r\n8 пара 20:05 - 21:35");
    }

    public LiveData<String> getText() {
        return mText;
    }
}