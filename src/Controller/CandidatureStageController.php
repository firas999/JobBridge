<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CandidatureStageController extends AbstractController
{
    /**
     * @Route("/candidature/stage", name="candidature_stage")
     */
    public function index(): Response
    {
        return $this->render('candidature_stage/index.html.twig', [
            'controller_name' => 'CandidatureStageController',
        ]);
    }
}
