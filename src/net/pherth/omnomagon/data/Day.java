/*
Copyright (c) 2012, Phillip Thelen
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package net.pherth.omnomagon.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;

public class Day {
	List<Pair<MealGroup, List<Meal>>> allMeals = new ArrayList<Pair<MealGroup, List<Meal>>>();
	public Date date;
	public String id;
	
	public Day(Date currentDay) {
		date = currentDay;
	}
	
	public Day(String currentDayString) {
		try {
			SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
			date = sdfToDate.parse(currentDayString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Day(String currentDayString, String dayid) {
		try {
			//Tue Mar 29 00:00:00 MESZ 12
			SimpleDateFormat sdfToDate = new SimpleDateFormat("E M dd HH:mm:ss z yy");
			Log.i("Day", currentDayString);
			date = sdfToDate.parse(currentDayString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		id = dayid;
	}
	
	public List<Pair<MealGroup, List<Meal>>> getMeals() {
		return allMeals;
	}
	
	public void addMealGroup(@NonNull MealGroup mealGroup) {
		allMeals.add(new Pair<MealGroup, List<Meal>>(mealGroup, new ArrayList<Meal>()));
	}
	
	public void addMealGroup(@NonNull MealGroup mealGroup, @NonNull List<Meal> meals) {
		allMeals.add(new Pair<MealGroup, List<Meal>>(mealGroup, meals));
	}
	
	public void addMealGroup(@NonNull Pair<MealGroup, List<Meal>> meals) {
		allMeals.add(meals);
	}
	
	public void addMeal(@NonNull MealGroup mealGroup, Meal meal) {
		boolean found = false;
		for (final Pair<MealGroup, List<Meal>> allMeal : allMeals) {
			if (allMeal.first == mealGroup) {
				allMeal.second.add(meal);
				found = true;
			}
		}
		
		if (!found) {
			List<Meal> mealList = new ArrayList<Meal>();
			mealList.add(meal);
			allMeals.add(new Pair<MealGroup, List<Meal>>(mealGroup, mealList));
		}
	}
	
	public void setMeals(List<Pair<MealGroup, List<Meal>>> meals) {
		allMeals = meals;
	}
}