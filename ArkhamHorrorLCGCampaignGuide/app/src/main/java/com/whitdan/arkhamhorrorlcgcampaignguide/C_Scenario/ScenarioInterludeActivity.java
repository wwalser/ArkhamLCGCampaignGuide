package com.whitdan.arkhamhorrorlcgcampaignguide.C_Scenario;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.whitdan.arkhamhorrorlcgcampaignguide.A_Menus.MainMenuActivity;
import com.whitdan.arkhamhorrorlcgcampaignguide.D_Misc.CampaignFinishedActivity;
import com.whitdan.arkhamhorrorlcgcampaignguide.R;
import com.whitdan.arkhamhorrorlcgcampaignguide.Z_Data.GlobalVariables;

import static android.view.View.VISIBLE;

public class ScenarioInterludeActivity extends AppCompatActivity {

    static GlobalVariables globalVariables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity_scenario_interlude);
        globalVariables = (GlobalVariables) this.getApplication();

        // Set typefaces
        TextView title = (TextView) findViewById(R.id.current_scenario_name);
        Typeface teutonic = Typeface.createFromAsset(getAssets(), "fonts/teutonic.ttf");
        title.setTypeface(teutonic);
        TextView introduction = (TextView) findViewById(R.id.introduction_text);
        Typeface arnoproitalic = Typeface.createFromAsset(getAssets(), "fonts/arnoproitalic.otf");
        introduction.setTypeface(arnoproitalic);
        final TextView introductionOne = (TextView) findViewById(R.id.introduction_text_additional_one);
        TextView introductionTwo = (TextView) findViewById(R.id.introduction_text_additional_two);
        TextView introductionThree = (TextView) findViewById(R.id.introduction_text_additional_three);
        TextView introductionFour = (TextView) findViewById(R.id.introduction_text_additional_four);
        TextView introductionFive = (TextView) findViewById(R.id.introduction_text_additional_five);
        TextView introductionSix = (TextView) findViewById(R.id.introduction_text_additional_six);
        introductionOne.setTypeface(arnoproitalic);
        introductionTwo.setTypeface(arnoproitalic);
        introductionThree.setTypeface(arnoproitalic);
        introductionFour.setTypeface(arnoproitalic);
        introductionFive.setTypeface(arnoproitalic);
        introductionSix.setTypeface(arnoproitalic);
        RadioButton introductionOptionOne = (RadioButton) findViewById(R.id.introduction_option_one);
        RadioButton introductionOptionTwo = (RadioButton) findViewById(R.id.introduction_option_two);
        Typeface arnopro = Typeface.createFromAsset(getAssets(), "fonts/arnopro.otf");
        introductionOptionOne.setTypeface(arnopro);
        introductionOptionTwo.setTypeface(arnopro);

        // Set text and apply any resolutions
        switch (globalVariables.CurrentCampaign) {
            case 2:
                switch (globalVariables.CurrentScenario) {
                    case 3:
                        title.setText(R.string.dunwich_interlude_one);
                        if (globalVariables.InvestigatorsUnconscious == 1) {
                            introduction.setText(R.string.armitage_interlude_one);
                            globalVariables.HenryArmitage = 0;
                            for (int i = 0; i < globalVariables.Investigators.size(); i++) {
                                globalVariables.Investigators.get(i).AvailableXP += 2;
                            }
                        } else {
                            introduction.setText(R.string.armitage_interlude_two);
                            globalVariables.HenryArmitage = 1;
                        }
                        break;
                    case 7:
                        title.setText(R.string.dunwich_interlude_two);
                        boolean powder = false;
                        introduction.setText(R.string.survivors_interlude);
                        if (globalVariables.HenryArmitage == 3) {
                            introductionOne.setVisibility(VISIBLE);
                            introductionOne.setText(R.string.survivors_interlude_one);
                            powder = true;
                        }
                        if (globalVariables.WarrenRice == 3) {
                            introductionTwo.setVisibility(VISIBLE);
                            introductionTwo.setText(R.string.survivors_interlude_two);
                            powder = true;
                        }
                        if (globalVariables.FrancisMorgan == 3) {
                            introductionThree.setVisibility(VISIBLE);
                            introductionThree.setText(R.string.survivors_interlude_three);
                            powder = true;
                        }
                        if (powder) {
                            introductionFour.setVisibility(VISIBLE);
                            introductionFour.setText(R.string.survivors_interlude_powder);
                        }
                        if (globalVariables.ZebulonWhateley == 0) {
                            introductionFive.setVisibility(VISIBLE);
                            introductionFive.setText(R.string.survivors_interlude_four);
                        }
                        if (globalVariables.EarlSawyer == 0) {
                            introductionSix.setVisibility(VISIBLE);
                            introductionSix.setText(R.string.survivors_interlude_five);
                        }
                        break;
                    case 11:
                        title.setText(R.string.dunwich_epilogue);
                        if (globalVariables.TownsfolkAction == 1) {
                            introduction.setText(R.string.dunwich_epilogue_one);
                        } else if (globalVariables.TownsfolkAction == 2) {
                            introduction.setText(R.string.dunwich_epilogue_two);
                        }
                        globalVariables.DunwichCompleted = 1;
                        break;
                }
                break;
            case 3:
                break;
        }

        if (globalVariables.CurrentScenario > 100) {
            switch (globalVariables.CurrentScenario) {
                case 101:
                    title.setText(R.string.rougarou_scenario);
                    introduction.setText(R.string.rougarou_introduction);
                    break;
                case 102:
                    title.setText(R.string.carnevale_scenario);
                    introduction.setText(R.string.carnevale_introduction);
                    break;
            }
        }

        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setTypeface(teutonic);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScenarioInterludeActivity.this, MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        Button continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setTypeface(teutonic);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                globalVariables.CurrentScenario += 1;
                Intent intent = new Intent(ScenarioInterludeActivity.this, ScenarioMainActivity.class);
                if (globalVariables.CurrentCampaign == 2 && globalVariables.DunwichCompleted == 1) {
                    intent = new Intent(ScenarioInterludeActivity.this, CampaignFinishedActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    // Makes back button go up (back to home page - SelectCampaignActivity)
    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
