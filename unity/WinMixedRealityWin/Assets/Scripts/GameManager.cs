﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour {

    public GameObject BasicModel;
    public GameObject FootballModel;
    public GameObject FunctionModel;
    public GameObject KleinModel;
    public GameObject LissajousModel;
    public GameObject PenroseModel;
    public GameObject RuledSurfaceModel;
    public GameObject SierpinskiModel;
    public GameObject TempModel;

    public GameObject ModelSpawner;

    public GameObject TempText;
    public GameObject DisplayedText;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    public void OnButtonPressed ()
    {
        //Destroy all GameObjects with tag "Model"
        GameObject[] gameObjects;  
        gameObjects = GameObject.FindGameObjectsWithTag("Model");
        for (var i = 0; i < gameObjects.Length; i++)
        {
            Destroy(gameObjects[i]);
        }
        
        // Create GameObject
        Instantiate(TempModel, ModelSpawner.transform.localPosition, Quaternion.identity);

        // Change Text on the screen

        //TempText.SetActive(false);
        //DisplayedText.SetActive(false);

        if (DisplayedText == null)
        {
            DisplayedText = TempText;
            DisplayedText.GetComponent<Text>().enabled = true;
        } else
        {
            DisplayedText.GetComponent<Text>().enabled = false;
            DisplayedText = TempText;
            //DisplayedText.SetActive(true);
            DisplayedText.GetComponent<Text>().enabled = true;
        }




    }

    public void CheckForItem(string ModelName)
    {
        Debug.Log(ModelName);
    }



}
