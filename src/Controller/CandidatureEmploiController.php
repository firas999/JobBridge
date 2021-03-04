<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CandidatureEmploiController extends AbstractController
{
    /**
     * @Route("/candidature/emploi", name="candidature_emploi")
     */
    public function index(): Response
    {
        return $this->render('candidature_emploi/index.html.twig', [
            'controller_name' => 'CandidatureEmploiController',
        ]);
    }
}
