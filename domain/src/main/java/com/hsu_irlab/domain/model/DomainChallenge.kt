package com.hsu_irlab.domain.model

import android.os.Parcel
import android.os.Parcelable

data class DomainChallenge (
    val challenge_id: Int,
    val title: String,
    val term: Int,
    val challenge_reward: Int,
    val participating_person: Int,
//    val is_participate: ChallengeDetail?,
    val user_challenge_id: Int?,
    val start_date: String?,
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(challenge_id)
        parcel.writeString(title)
        parcel.writeInt(term)
        parcel.writeInt(challenge_reward)
        parcel.writeInt(participating_person)
        parcel.writeValue(user_challenge_id)
        parcel.writeString(start_date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DomainChallenge> {
        override fun createFromParcel(parcel: Parcel): DomainChallenge {
            return DomainChallenge(parcel)
        }

        override fun newArray(size: Int): Array<DomainChallenge?> {
            return arrayOfNulls(size)
        }
    }

}
data class ChallengeDetail(
    val user_challenge_id: Int,
    val start_date: String,
)