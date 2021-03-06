/*
 * Copyright (C) 2014 Mobs & Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobsandgeeks.saripaar.tests.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.tests.CustomAnnotationActivity;
import com.mobsandgeeks.saripaar.tests.R;

public class CustomAnnotationTest
        extends ActivityInstrumentationTestCase2<CustomAnnotationActivity> {

    private TextView mResultTextView;

    public CustomAnnotationTest() {
        super(CustomAnnotationActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mResultTextView = (TextView) getActivity().findViewById(R.id.resultTextView);
    }

    // Using 'testX' prefix, because of static variables in Validator > Registry.
    public void test0UnregisteredAnnotationWithNoOtherRules_crash() {
        TestHelper.type(R.id.zipCodeEditText, Constants.ZIP_CODE);
        TestHelper.clickView(R.id.saripaarButton);
        TestHelper.checkForText(Constants.STATE_CRASH, mResultTextView);
    }

    public void test1ValidZipCode_success() {
        TestHelper.clickView(R.id.registerAnnotationRadioButton);
        TestHelper.type(R.id.zipCodeEditText, Constants.ZIP_CODE);
        TestHelper.clickView(R.id.saripaarButton);
        TestHelper.checkForText(Constants.STATE_SUCCESS, mResultTextView);
    }

    public void test2InvalidZipCode_failure() {
        TestHelper.clickView(R.id.registerAnnotationRadioButton);
        TestHelper.type(R.id.zipCodeEditText, "600018");
        TestHelper.clickView(R.id.saripaarButton);
        TestHelper.checkForText(Constants.FIELD_ZIP_CODE, mResultTextView);
    }
}
