package com.hsu_irlab.domain.model

import android.os.Parcel
import android.os.Parcelable

data class DomainCampaign(
    val campaign_id: Int,
    val title: String,
    val start_date: String,
    val end_date: String,
    val detail: String,
    val poster_img: String,
    val campaign_reward: Int,
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(campaign_id)
        parcel.writeString(title)
        parcel.writeString(start_date)
        parcel.writeString(end_date)
        parcel.writeString(detail)
        parcel.writeString(poster_img)
        parcel.writeInt(campaign_reward)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DomainCampaign> {
        override fun createFromParcel(parcel: Parcel): DomainCampaign {
            return DomainCampaign(parcel)
        }

        override fun newArray(size: Int): Array<DomainCampaign?> {
            return arrayOfNulls(size)
        }
    }
}