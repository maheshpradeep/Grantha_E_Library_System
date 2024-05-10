package com.example.grantha;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CusAdapMember extends RecyclerView.Adapter<CusAdapMember.MemberViewHolder> {
    private Context context;
    private ArrayList<String> memberCardNo, memberFirstName, memberLastName, memberMobileNumber, memberAddress, memberUnpaidDues;

    public CusAdapMember(Context context, ArrayList<String> memberCardNo, ArrayList<String> memberFirstName,
                         ArrayList<String> memberLastName, ArrayList<String> memberMobileNumber,
                         ArrayList<String> memberAddress, ArrayList<String> memberUnpaidDues) {
        this.context = context;
        this.memberCardNo = memberCardNo;
        this.memberFirstName = memberFirstName;
        this.memberLastName = memberLastName;
        this.memberMobileNumber = memberMobileNumber;
        this.memberAddress = memberAddress;
        this.memberUnpaidDues = memberUnpaidDues;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rowmember, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder,@SuppressLint("RecyclerView") int position) {
        holder.memberCardNoTxt.setText(memberCardNo.get(position));
        holder.memberFirstNameTxt.setText(memberFirstName.get(position));
        holder.memberLastNameTxt.setText(memberLastName.get(position));
        holder.memberMobileTxt.setText(memberMobileNumber.get(position));
        holder.memberAddressTxt.setText(memberAddress.get(position));
        holder.memberUnpaidDuesTxt.setText(memberUnpaidDues.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, updatemembers.class);
                intent.putExtra("cardNo", memberCardNo.get(position));
                intent.putExtra("firstName", memberFirstName.get(position));
                intent.putExtra("lastName", memberLastName.get(position));
                intent.putExtra("mobileNumber", memberMobileNumber.get(position));
                intent.putExtra("address", memberAddress.get(position));
                intent.putExtra("unpaidDues", memberUnpaidDues.get(position));
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return memberCardNo.size();
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder {
        TextView memberCardNoTxt, memberFirstNameTxt,memberLastNameTxt, memberMobileTxt, memberAddressTxt, memberUnpaidDuesTxt;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            memberCardNoTxt = itemView.findViewById(R.id.member_card_no_text);
            memberFirstNameTxt = itemView.findViewById(R.id.member_first_name);
            memberLastNameTxt =itemView.findViewById(R.id.member_last_name);
            memberMobileTxt = itemView.findViewById(R.id.member_mobile);
            memberAddressTxt = itemView.findViewById(R.id.member_address);
            memberUnpaidDuesTxt = itemView.findViewById(R.id.member_unpaid_dues);

            // Set click listener
        }


    }

    // Method to delete a member
    void deleteMember(int position) {
        DatabaseLibrary myDB = new DatabaseLibrary(context);
        myDB.deleteMember(memberCardNo.get(position));
        memberCardNo.remove(position);
        memberFirstName.remove(position);
        memberLastName.remove(position);
        memberMobileNumber.remove(position);
        memberAddress.remove(position);
        memberUnpaidDues.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, memberCardNo.size());
    }
}
